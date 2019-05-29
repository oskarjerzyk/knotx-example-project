# Knot.x Docker For Workshop

## Run
Run Knot.x instance and example data services (Web API and Content Repository) in a single node Docker Swarm:
```
docker stack deploy -c knotx-workshop.yml workshop
```

## Examples

**Simple**
Renders a page that is processed by `content-get` task.
http://localhost:8092/content/index.html