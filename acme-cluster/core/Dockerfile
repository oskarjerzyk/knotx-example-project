FROM knotx/acme:latest

## Copy ACME specific configs
ADD conf ${KNOTX_HOME}/conf

EXPOSE 8092

ENTRYPOINT ["knotx"]
CMD ["run-knotx", "-cluster"]
