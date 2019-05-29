# Knot.x Docker For Workshop

## Run
Pull Knot.x workshop image:
```
docker pull skejven/knotx-2-workshop:1
```

Run Knot.x instance and example data services (Web API and Content Repository) in a single node Docker Swarm:
```
docker stack deploy -c knotx-workshop.yml workshop
```

## Examples

**Simple**
Renders a page that is processed by `content-get` task.

- Example final page: http://localhost:8092/content/index.html
- Example content (template): http://localhost:4503/content/index.html
- Example WebAPI: http://localhost:3000/api/v1/hello.json

